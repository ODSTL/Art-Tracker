package com.example.ArtTracker.controllers;

import javax.validation.Valid;
import com.example.ArtTracker.models.Location;
import com.example.ArtTracker.models.data.LocationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping({"location"})
public class LocationController {
    @Autowired
    public LocationDao locationDao;

    public LocationController() {
    }

    @RequestMapping({""})
    public String index(Model model) {
        model.addAttribute("locations", this.locationDao.findAll());
        model.addAttribute("title", "Locations");
        return "location/index";
    }

    @RequestMapping(
            value = {"add"},
            method = {RequestMethod.GET}
    )
    public String displayAddCategoryForm(Model model) {
        model.addAttribute("title", "Add Location");
        model.addAttribute(new Location());
        return "location/add";
    }

    @RequestMapping(
            value = {"add"},
            method = {RequestMethod.POST}
    )
    public String processAddCategoryForm(@ModelAttribute @Valid Location newLocation, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Location");
            return "location/add";
        } else {
            this.locationDao.save(newLocation);
            return "redirect:";
        }
    }
}
