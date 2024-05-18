package alvarez.wilfredo.reactivemongodb.dist.rest.user;

import alvarez.wilfredo.reactivemongodb.service.user.UserService;
import alvarez.wilfredo.reactivemongodb.service.user.datasource.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<Mono<User>> create(@RequestBody User user) {
        Mono<User> userMono = userService.createUser(user);
        if (Objects.isNull(userMono)) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(userMono, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Flux<User>> getAllUsers() {
        Flux<User> userFlux = userService.getAllUsers();
        if (Objects.isNull(userFlux)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userFlux, HttpStatus.FOUND);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Mono<User>> getUserById(@PathVariable String userId) {
        Mono<User> userMono = userService.findById(userId);
        if (Objects.isNull(userMono)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userMono, HttpStatus.FOUND);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Mono<User>> updateUserById(@PathVariable String userId, @RequestBody User user) {
        Mono<User> userMono = userService.updateUser(userId, user);
        if (Objects.isNull(userMono)) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        return new ResponseEntity<>(userMono, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Mono<User>> deleteUserById(@PathVariable String userId) {
        Mono<User> userMono = userService.deleteUser(userId);
        if (Objects.isNull(userMono)) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(userMono, HttpStatus.OK);
    }
}
