package com.nnk.springboot.controllers;

import com.nnk.springboot.dto.BidListDto;
import com.nnk.springboot.service.IBidListService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import javax.validation.Valid;


@Controller
public class BidListController {

    private final IBidListService bidListService;

    public BidListController(IBidListService bidListService) {
        this.bidListService = bidListService;
    }

    @GetMapping("/bidList/list")
    public String home(Model model)
    {

        model.addAttribute("bidLists", bidListService.getBidLists());
        return "bidList/list";
    }

    @GetMapping("/bidList/add")
    public String addBidForm( @ModelAttribute("bidList") BidListDto bidListDto) {
        return "bidList/add";
    }

    @PostMapping("/bidList/validate")
    public String validate(@Valid @ModelAttribute("bidList") BidListDto bidListDto, BindingResult result, Model model) {
        if (!result.hasErrors()) {
        bidListService.addBidList(bidListDto);
        model.addAttribute("bidLists", bidListService.getBidLists());
        return "redirect:/bidList/list";
        }
        return "bidList/add";
    }

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("bidList", bidListService.getBidById(id));
        return "bidList/update";
    }

    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid  @ModelAttribute("bidList") BidListDto bidListDto,
                             BindingResult result, Model model) {
         if (result.hasErrors()) {
            return "bidList/update";

        }
        bidListDto.setId(id);
        bidListDto.setAccount(bidListDto.getAccount());
        bidListDto.setType(bidListDto.getType());
        bidListDto.setBidQuantity(bidListDto.getBidQuantity());
        bidListService.addBidList(bidListDto);
        model.addAttribute("bidLists", bidListService.getBidLists());
        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        bidListService.deleteBidById(id);
        model.addAttribute("bidLists", bidListService.getBidLists());
        return "redirect:/bidList/list";
    }
}
