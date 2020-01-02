package com.example.ArtTracker.controllers;

import com.example.ArtTracker.models.data.ArtworkDao;
import com.example.ArtTracker.models.data.DisplayDao;
import com.example.ArtTracker.models.forms.AddDisplayItemForm;
import com.example.ArtTracker.models.Artwork;
import com.example.ArtTracker.models.Display;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "menu")
public class DisplayController {

    @Autowired
    private
    DisplayDao displayDao;

    @Autowired
    private
    ArtworkDao artworkDao;

    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("title", "Displays");
        model.addAttribute("displays", displayDao.findAll());
        return "display/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("title", "Add Display");
        model.addAttribute(new Display());
        return "display/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid Display display, Errors errors) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Display");
            return "display/add";
        }

        displayDao.save(display);

        return "redirect:view/" + display.getId();

    }

    @RequestMapping(value = "view/{displayId}", method = RequestMethod.GET)
    public String addItem(Model model, @PathVariable int displayId) {

        Display display = displayDao.findById(displayId).get();



        model.addAttribute("title", "add item to display: " + display.getName());
        model.addAttribute("artworks", display.getArtworks());
        model.addAttribute("displayId" , displayId);
        return "display/view";

    }

    @RequestMapping(value = "add-item", method = RequestMethod.POST)
    public String addItem(Model model,
                          @ModelAttribute @Valid AddDisplayItemForm form, Errors errors) {

        if (errors.hasErrors()) {
            model.addAttribute("form", form);
            return "display/add-item";
        }

        Artwork theArtwork = artworkDao.findById(form.getArtworkId()).get();
        Display theDisplay = displayDao.findById(form.getDisplayId()).get();
        theDisplay.addItem(theArtwork);
        displayDao.save(theDisplay);

        return "redirect:/display/view/" + theDisplay.getId();
    }

    @RequestMapping(value = "add-item/{displayId}", method = RequestMethod.GET)
    public String addItems(Model model, @PathVariable int displayId ) {


        Display display = displayDao.findById(displayId).get();

        AddDisplayItemForm form = new AddDisplayItemForm(artworkDao.findAll(), display);


        model.addAttribute("form",form);
        return "menu/add-item";
    }
}
