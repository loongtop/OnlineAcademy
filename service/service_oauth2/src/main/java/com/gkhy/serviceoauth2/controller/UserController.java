package com.gkhy.serviceoauth2.controller;

import com.gkhy.servicebase.result.Result;
import com.gkhy.servicebase.utils.ItemFound;
import com.gkhy.serviceoauth2.entity.User;
import com.gkhy.serviceoauth2.entity.UserPrincipal;
import com.gkhy.serviceoauth2.entity.annotation.CurrentUser;

import com.gkhy.serviceoauth2.entity.enums.AuthProvider;
import com.gkhy.serviceoauth2.entity.request.RegisterRequest;
import com.gkhy.serviceoauth2.entity.request.SignInRequest;
import com.gkhy.serviceoauth2.error.Oauth2Error;
import com.gkhy.serviceoauth2.service.UserService;
import com.gkhy.serviceoauth2.token.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;


/**
 * <p>
 * Membership Form Front Controller
 * </p>
 *
 * @author leo
 * @since 2022-07-20
 */
@RestController
@RequestMapping("/oauth2")
@CrossOrigin
public class UserController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    @Autowired
    public UserController(AuthenticationManager authenticationManager, UserService userDetailsService, PasswordEncoder passwordEncoder, TokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
    }

    //user login account
    @PostMapping("/login")
    public Result login(@Valid @RequestBody SignInRequest loginRequest) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenProvider.createToken(authentication);
        return Result.success().data("token", token);
    }

    //register a user
    @PostMapping("/register")
    public Result register(@Valid @RequestBody RegisterRequest registerRequest) {
        if(userService.existsByColumnName("email", registerRequest.getEmail())) {
            return Result.fail().data("message", Oauth2Error.EMAIL_OCCUPIED);
        }

        // Creating a user's account
        User user = new User();
        user.setUsername(registerRequest.getName());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setProvider(AuthProvider.LOCAL);

        User academyUser = userService.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/user/current")
                .buildAndExpand(academyUser.getId()).toUri();

        return Result.success().data("item", academyUser)
                .data("URI", location).data("message", "Register successfully!");
    }

    //Get user information based on token
    @GetMapping("getMemberInfo")
    public Result getMemberInfo(HttpServletRequest request) {
        //Call the method of the jwt tool class.
        // Get the header information according to the request object and return the user id
        String token = request.getHeader("token");
        String memberId = tokenProvider.getUserIdFromToken(token);
        //Query the database to obtain user information based on user id
        Optional<User> member = userService.findById(Long.valueOf(memberId));
        if (member.isEmpty()) return ItemFound.fail();

        return Result.success().data("userInfo", member);
    }

    @PostMapping("getUserInfoOrder/{id}")
    public Result getUserInfoOrder(@PathVariable Long id) {
        Optional<User> user = userService.findById(id);
        if (user.isEmpty()) return ItemFound.fail();

        return Result.success().data("userInfo", user);
    }

    @GetMapping("/user/current")
    @PreAuthorize("hasRole('USER')")
    public Optional<User> getCurrentUser(@CurrentUser UserPrincipal userFactory) {

        return userService.findById(userFactory.getId());
    }

}
