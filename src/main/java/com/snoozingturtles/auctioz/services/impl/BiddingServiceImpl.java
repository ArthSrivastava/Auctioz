package com.snoozingturtles.auctioz.services.impl;

import com.snoozingturtles.auctioz.dto.BiddingDto;
import com.snoozingturtles.auctioz.dto.ProductDto;
import com.snoozingturtles.auctioz.exceptions.EntityNotFoundException;
import com.snoozingturtles.auctioz.models.Bidding;
import com.snoozingturtles.auctioz.repositories.BiddingRepo;
import com.snoozingturtles.auctioz.services.BiddingService;
import com.snoozingturtles.auctioz.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BiddingServiceImpl implements BiddingService {
    private final BiddingRepo biddingRepo;
    private final ProductService productService;
    private final ModelMapper modelMapper;

    @Override
    public BiddingDto createBidding(String productId, String sellerId, BiddingDto biddingDto) {
        ProductDto productById = productService.getProductById(productId, sellerId);
        Bidding bidding = modelMapper.map(biddingDto, Bidding.class);
        bidding.setProductId(productId);
        Bidding savedBid = biddingRepo.save(bidding);

        productById.setBiddingId(savedBid.getId());
        productService.updateProduct(productId, sellerId, productById);
        return modelMapper.map(savedBid, BiddingDto.class);
    }

    @Override
    public void updateBidding(String productId, String sellerId, BiddingDto biddingDto) {
        BiddingDto biddingById = getBiddingById(productId, sellerId);
        biddingById.setStartBidPrice(biddingDto.getStartBidPrice());
        biddingById.setDeadline(biddingById.getDeadline());
        biddingRepo.save(modelMapper.map(biddingById, Bidding.class));
    }

    @Override
    public BiddingDto getBiddingById(String productId, String sellerId) {
        productService.getProductById(productId, sellerId);
        return modelMapper.map(
                biddingRepo.findByProductId(productId)
                        .orElseThrow(() -> new EntityNotFoundException("No such bidding found!")),
                BiddingDto.class
        );
    }

    @Override
    public List<BiddingDto> getAllBiddings() {
        return biddingRepo.findAll()
                .stream()
                .map(bidding -> modelMapper.map(bidding, BiddingDto.class))
                .toList();
    }

    @Override
    public void deleteBidding(String productId, String sellerId) {
        BiddingDto biddingById = getBiddingById(productId, sellerId);
        biddingRepo.delete(modelMapper.map(biddingById, Bidding.class));
    }
}
