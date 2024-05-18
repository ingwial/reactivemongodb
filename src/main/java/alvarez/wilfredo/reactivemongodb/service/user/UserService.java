package alvarez.wilfredo.reactivemongodb.service.user;

import alvarez.wilfredo.reactivemongodb.service.user.datasource.UserRepository;
import alvarez.wilfredo.reactivemongodb.service.user.datasource.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public Mono<User> createUser(User user) {
        return userRepository.save(user);
    }

    public Flux<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Mono<User> findById(String userId) {
        return userRepository.findById(userId);
    }

    public Mono<User> updateUser(String userId, User user) {
        return userRepository.findById(userId)
                .flatMap(dbUser -> {
                    dbUser.setRut(userId);
                    dbUser.setName(user.getName());
                    dbUser.setLastName(user.getLastName());
                   return userRepository.save(dbUser);
                });
    }

    public Mono<User> deleteUser(String userId) {
        return userRepository.findById(userId)
                .flatMap(dbUser -> userRepository.delete(dbUser)
                        .then(Mono.just(dbUser)));
    }
}
