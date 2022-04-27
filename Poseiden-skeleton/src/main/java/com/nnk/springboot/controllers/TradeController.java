package com.nnk.springboot.controllers;

import com.nnk.springboot.dto.TradeDto;
import com.nnk.springboot.service.ITradeService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class TradeController {

    private final ITradeService tradeService;

    public TradeController(ITradeService tradeService) {
        this.tradeService = tradeService;
    }

    @RequestMapping("/trade/list")
    public String home(Model model)
    {
        model.addAttribute("trades", tradeService.getTrades());
        return "trade/list";
    }

    @GetMapping("/trade/add")
    public String addTrade(@ModelAttribute("trade") TradeDto tradeDto) {
        return "trade/add";
    }

    @PostMapping("/trade/validate")
    public String validate(@Valid @ModelAttribute("trade") TradeDto tradeDto, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            tradeService.addTrade(tradeDto);
            model.addAttribute("trades", tradeService.getTrades());
            return "redirect:/trade/list";
        }
        return "trade/add";
    }

    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("trade", tradeService.getTradeById(id));
        return "trade/update";
    }

    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid @ModelAttribute("trade") TradeDto tradeDto,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "trade/update";
        }
        tradeDto.setId(tradeDto.getId());
        tradeDto.setAccount(tradeDto.getAccount());
        tradeDto.setType(tradeDto.getType());
        tradeDto.setBuyQuantity(tradeDto.getBuyQuantity());
        tradeService.addTrade(tradeDto);
        model.addAttribute("trades", tradeService.getTrades());
        return "redirect:/trade/list";
    }

    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {
        tradeService.deleteTradeById(id);
        model.addAttribute("trades", tradeService.getTrades());
        return "redirect:/trade/list";
    }
}
