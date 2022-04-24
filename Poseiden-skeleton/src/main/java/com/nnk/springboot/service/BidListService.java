package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.dto.BidListDto;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.util.IConversion;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BidListService implements IBidListService{

    private final BidListRepository bidListRepository;

    private final IConversion conversion;

    public BidListService(BidListRepository bidListRepository, IConversion conversion) {
        this.bidListRepository = bidListRepository;
        this.conversion = conversion;
    }

    public List<BidListDto> getBidLists() {
       List<BidList> bidLists = bidListRepository.findAll();
        return bidLists.stream()
                .map(conversion::bidListToBidListDto)
                .collect(Collectors.toList());

   }

   public void addBidList(BidList bidList) {
     bidListRepository.save(bidList);

   }

   public BidListDto getBidById(Integer id) {
       if(bidListRepository.existsById(id)){
           return conversion.bidListToBidListDto(bidListRepository.getById(id));

       } else {
           throw new IllegalArgumentException("Invalid bid Id:" + id);
       }

   }

   public void deleteBidById(Integer id) {
       if(bidListRepository.existsById(id)){
           bidListRepository.delete(bidListRepository.getById(id));
       } else {
           throw new IllegalArgumentException("Invalid bid Id:" + id);
       }
   }



}
