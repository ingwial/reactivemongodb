package alvarez.wilfredo.reactivemongodb.service.user.datasource;

import alvarez.wilfredo.reactivemongodb.service.user.datasource.domain.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {
}
