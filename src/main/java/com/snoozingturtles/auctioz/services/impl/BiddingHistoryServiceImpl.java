package com.snoozingturtles.auctioz.services.impl;

import com.snoozingturtles.auctioz.dto.BiddingDto;
import com.snoozingturtles.auctioz.dto.BiddingHistoryDto;
import com.snoozingturtles.auctioz.exceptions.EntityNotFoundException;
import com.snoozingturtles.auctioz.exceptions.InvalidBidException;
import com.snoozingturtles.auctioz.models.Bidding;
import com.snoozingturtles.auctioz.models.BiddingHistory;
import com.snoozingturtles.auctioz.repositories.BiddingHistoryRepo;
import com.snoozingturtles.auctioz.repositories.BiddingRepo;
import com.snoozingturtles.auctioz.services.BiddingHistoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BiddingHistoryServiceImpl implements BiddingHistoryService {
    private final BiddingHistoryRepo biddingHistoryRepo;
    private final ModelMapper modelMapper;
    private final BiddingRepo biddingRepo;

    @Override
    public void createBid(String productId, String userId, BiddingHistoryDto biddingHistoryDto) {
        BiddingDto biddingById = getBiddingDto(productId);

        checkValidBidPrice(biddingHistoryDto.getBiddingAmount(), biddingById.getCurrentBidPrice());
        updateBiddingDetails(biddingHistoryDto, biddingById, userId);
        biddingRepo.save(modelMapper.map(biddingById, Bidding.class));

        biddingHistoryDto.setProductId(productId);
        biddingHistoryDto.setUserId(userId);
        biddingHistoryDto.setTimestamp(LocalDateTime.now());
        biddingHistoryRepo.save(modelMapper.map(biddingHistoryDto, BiddingHistory.class));
    }

    private static void updateBiddingDetails(BiddingHistoryDto biddingHistoryDto, BiddingDto biddingById, String userId) {
        biddingById.setCurrentBidPrice(biddingHistoryDto.getBiddingAmount());
        biddingById.setCurrentBidderId(userId);
    }

    @Override
    public void updateBid(String productId, String userId, String bidId, BiddingHistoryDto biddingHistoryDto) {
        BiddingDto biddingById = getBiddingDto(productId);

        checkValidBidPrice(biddingHistoryDto.getBiddingAmount(), biddingById.getCurrentBidPrice());
        updateBiddingDetails(biddingHistoryDto, biddingById, userId);
        biddingRepo.save(modelMapper.map(biddingById, Bidding.class));

        BiddingHistoryDto biddingHistory = getBidOfProductByUserIdAndBidId(productId, userId, bidId);
        biddingHistory.setBiddingAmount(biddingHistoryDto.getBiddingAmount());
        biddingHistory.setTimestamp(LocalDateTime.now());
        biddingHistoryRepo.save(modelMapper.map(biddingHistory, BiddingHistory.class));
    }

    private BiddingDto getBiddingDto(String productId) {
        return modelMapper.map(
                biddingRepo.findByProductId(productId)
                        .orElseThrow(() -> new EntityNotFoundException("No such bidding found!")),
                BiddingDto.class
        );
    }

    @Override
    public List<BiddingHistoryDto> getAllBidsOfProduct(String productId) {
        return biddingHistoryRepo.findAllByProductId(productId)
                .stream()
                .map(biddingHistory -> modelMapper.map(biddingHistory, BiddingHistoryDto.class))
                .toList();
    }

    @Override
    public List<BiddingHistoryDto> getAllBiddingsOfUser(String userId) {
        return biddingHistoryRepo.findAllByUserId(userId)
                .stream()
                .map(biddingHistory -> modelMapper.map(biddingHistory, BiddingHistoryDto.class))
                .toList();
    }

    @Override
    public List<BiddingHistoryDto> getAllBidsOfProductByUserId(String productId, String userId) {
        return biddingHistoryRepo.findAllByProductIdAndUserId(productId, userId)
                .stream()
                .map(biddingHistory -> modelMapper.map(biddingHistory, BiddingHistoryDto.class))
                .toList();
    }

    @Override
    public BiddingHistoryDto getBidOfProductByUserIdAndBidId(String productId, String userId, String bidId) {
        BiddingHistory biddingHistory = biddingHistoryRepo.findBiddingHistoryByProductIdAndUserIdAndId(productId, userId, bidId)
                .orElseThrow(() -> new EntityNotFoundException("No such bid found!"));
        return modelMapper.map(biddingHistory, BiddingHistoryDto.class);
    }

    @Override
    public void deleteBidByProductIdAndUserIdAndBidId(String productId, String userId, String bidId) {
        BiddingHistory biddingHistory = modelMapper.map(getBidOfProductByUserIdAndBidId(productId, userId, bidId), BiddingHistory.class);
        biddingHistoryRepo.delete(biddingHistory);
    }

    private static void checkValidBidPrice(String bidPrice, String currPrice) {
        long numBidPrice = Long.parseLong(bidPrice);
        long numCurrPrice = Long.parseLong(currPrice);
        if (numBidPrice <= numCurrPrice) {
            throw new InvalidBidException("Place a higher bid than current bid!");
        }
    }
}
