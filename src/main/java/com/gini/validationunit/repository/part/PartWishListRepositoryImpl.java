package com.gini.validationunit.repository.part;

import com.gini.validationunit.domaine.Part;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class PartWishListRepositoryImpl implements PartWishListRepository {

    private final EntityManager entityManager;

    @Override
    public void savePart(Part part) {
        entityManager.persist(part);
    }

    @Override
    public Optional<String> findPartNumber(String username, String partNumber) {

        String jpql = """
                SELECT part.partId
                    FROM Part part
                    JOIN part.users AS user
                WHERE part.partNumber = :partNumber AND user.username = :username
                """;

        return entityManager.createQuery(jpql, String.class)
                .setParameter("username", username)
                .setParameter("partNumber", partNumber)
                .getResultStream()
                .findFirst();
    }

    @Override
    public Set<Part> findAllParts(String username) {

        String jpql = """
                SELECT part FROM Part part
                    JOIN part.users AS user
                WHERE user.username = :username
                """;

        return  entityManager.createQuery(jpql, Part.class)
                .setParameter("username", username)
                .getResultStream()
                .collect(Collectors.toSet());
    }


}
