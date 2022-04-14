package com.gini.validationunit.service.services.wishlist.part;

import com.gini.validationunit.convertor.PartConvertor;
import com.gini.validationunit.domaine.postgress.Part;
import com.gini.validationunit.domaine.postgress.User;
import com.gini.validationunit.dto.response.part.PartWishResponse;
import com.gini.validationunit.errors.exception.UserNotFoundException;
import com.gini.validationunit.repository.part.PartWishListRepository;
import com.gini.validationunit.repository.user.UserWishListRepository;
import com.gini.validationunit.service.PartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PartWishListServiceImpl implements PartWishListService{

    private final PartService partService;
    private final UserWishListRepository userRepository;
    private final PartWishListRepository partWishListRepository;


    @Override
    @Transactional
    public PartWishResponse addPartToWishlist(String partNumber, String username){

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> {throw new UserNotFoundException("user not found");});

        checkIfPArtIsInWishList(partNumber, username);

        var findPartWithCurrencies = partService.findPartByPartNumber(partNumber);

        Part part = PartConvertor.convertToPart(findPartWithCurrencies);

        part.setUsers(Set.of(user));
        user.setParts(Set.of(part));

        partWishListRepository.savePart(part);

        //:todo -> return saved part
        return null;

    }

    @Override
    @Transactional
    public Set<PartWishResponse> findAllPatsForUser(String username){

        return partWishListRepository
                    .findAllParts(username)
                    .stream()
                    .map(PartConvertor::convertToPartWishResponse)
                    .collect(Collectors.toSet());
    }

    private void checkIfPArtIsInWishList(String partNumber, String username) {
        partWishListRepository.findPartNumber(username, partNumber)
                .ifPresent(x -> {throw new RuntimeException("part already added to wishlist");});
    }


}
