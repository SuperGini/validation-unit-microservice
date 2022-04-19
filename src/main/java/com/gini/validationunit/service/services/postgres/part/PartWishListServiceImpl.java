package com.gini.validationunit.service.services.postgres.part;

import com.gini.avro.data.PartPriceUpdateWithCurrency;
import com.gini.validationunit.convertor.PartConvertor;
import com.gini.validationunit.domaine.Part;
import com.gini.validationunit.domaine.User;
import com.gini.validationunit.dto.response.part.PartWishResponse;
import com.gini.validationunit.errors.exception.PartAlreadyInWishlist;
import com.gini.validationunit.errors.exception.UserNotFoundException;
import com.gini.validationunit.repository.part.PartWishListRepository;
import com.gini.validationunit.repository.user.UserWishListRepository;
import com.gini.validationunit.service.PartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
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

    @Override
    @Transactional
    public int updateAllPartPrices(PartPriceUpdateWithCurrency part){
        var priceEUR = new BigDecimal(part.getPriceEURO());
        var priceUSD = new BigDecimal(part.getPriceUSD());
        var priceRON = new BigDecimal(part.getPriceRON());

        return partWishListRepository
                .updatePricesOfPart(priceEUR, priceUSD, priceRON, part.getPartNumber());

    }

    private void checkIfPArtIsInWishList(String partNumber, String username) {
        partWishListRepository.findPartNumber(username, partNumber)
                .ifPresent(x -> {throw new PartAlreadyInWishlist("part already added to wishlist");});
    }


}
