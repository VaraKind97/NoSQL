package ru.netology.nosql.repository;

import ru.netology.nosql.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {
    List<User> findByName(String name);
    List<User> findByAge(Integer age);
    List<User> findByNameContaining(String name);
    List<User> findByAgeGreaterThanEqual(Integer age);
}
