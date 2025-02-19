package com.example.Restaurant.Controller;

import com.example.Restaurant.Repository.categoryRepository;
import com.example.Restaurant.Repository.menuItemRepository;
import com.example.Restaurant.Entity.MenuItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/menu")
public class MenuController {

    @Autowired
     menuItemRepository menuItemRepository;

    @Autowired
    categoryRepository categoryRepository;
    // 1) Menü öğelerini listeleme
    @GetMapping
    public String listMenuItems(Model model) {
        List<MenuItem> menuItems = menuItemRepository.findAll();
        model.addAttribute("menuItems", menuItems);
        
        // Artık redirect kullanmıyoruz, doğrudan "menu" adlı template'e gidiyoruz
        // (=> src/main/resources/templates/menu.html)
        return "menu";
    }

    // 2) Yeni menü öğesi formunu gösterme
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("menuItem", new MenuItem());
        model.addAttribute("categories", categoryRepository.findAll());

        // menu-form.html sayfasını gösteriyoruz
        return "menu-form";
    }
    

    // 3) Yeni menü öğesini kaydetme
    @PostMapping
    public String createMenuItem(@ModelAttribute MenuItem menuItem) {
        menuItemRepository.save(menuItem);
        // Kayıt tamamlanınca listeye dönmek için redirect
        return "redirect:/menu";
    }

    // 4) Düzenleme formunu gösterme
    @GetMapping("/{id}/edit")
    public String editMenuItem(@PathVariable Long id, Model model) {
        Optional<MenuItem> menuItemOptional = menuItemRepository.findById(id);
        if (menuItemOptional.isPresent()) {
            model.addAttribute("menuItem", menuItemOptional.get());
            model.addAttribute("categories", categoryRepository.findAll()); // Kategorileri ekle

            // Aynı menu-form.html şablonunu düzenleme için kullanabilirsiniz
            return "menu-form";
        } else {
            // Menü öğesi bulunamazsa liste sayfasına yönlendirin
            return "redirect:/menu";
        }
    }

    // 5) Güncelleme işlemi
    @PostMapping("/{id}")
    public String updateMenuItem(@PathVariable Long id, @ModelAttribute MenuItem menuItem) {
        menuItemRepository.findById(id).ifPresent(existingItem -> {
            existingItem.setName(menuItem.getName());
            existingItem.setDescription(menuItem.getDescription());
            existingItem.setPrice(menuItem.getPrice());
            existingItem.setCategory(menuItem.getCategory()); // Kategoriyi güncelle

            menuItemRepository.save(existingItem);
        });
        return "redirect:/menu";
    }

    // 6) Silme işlemi
    @GetMapping("/{id}/delete")
    public String deleteMenuItem(@PathVariable Long id) {
        menuItemRepository.deleteById(id);
        return "redirect:/menu";
    }
}
