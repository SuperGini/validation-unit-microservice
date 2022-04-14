package com.gini.validationunit.repository.user;

import com.gini.validationunit.domaine.postgress.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class UserWishListRepositoryImpl implements UserWishListRepository {


    private final EntityManager entityManager;

    @Override
    public void saveUser(User user){
        entityManager.persist(user);
    }

    @Override
    public Optional<User> findByUsername(String username){

        String jpql = "SELECT u FROM User u WHERE u.username = :username";


        return entityManager.createQuery(jpql, User.class)
                .setParameter("username", username)
                .getResultStream()
                .findFirst();

    }
}
