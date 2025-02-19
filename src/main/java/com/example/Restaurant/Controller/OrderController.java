package com.example.Restaurant.Controller;

import com.example.Restaurant.Entity.MenuItem;
import com.example.Restaurant.Entity.Order;
import com.example.Restaurant.Entity.OrderItem;
import com.example.Restaurant.Repository.menuItemRepository;
import com.example.Restaurant.Repository.orderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    orderRepository orderRepository;

    @Autowired
    menuItemRepository menuItemRepository;



    /**
     * 1) Tüm siparişleri listele
     */
    @GetMapping
    public String listOrders(Model model) {
        List<Order> orders = orderRepository.findAll();
        model.addAttribute("orders", orders);
        return "order-list";  // => src/main/resources/templates/order-list.html
    }

    /**
     * 2) Yeni sipariş formu
     *    Kullanıcının ekleyebileceği menü öğelerini gösteriyoruz.
     */
    @GetMapping("/new")
    public String showOrderForm(Model model) {
        List<MenuItem> menuItems = menuItemRepository.findAll();
        model.addAttribute("menuItems", menuItems);
        return "order-form"; // => Siparişi oluşturmak için bir form (templates/order-form.html)
    }

    /**
     * 3) Yeni sipariş oluşturma
     *    Burada örnek olarak tek bir MenuItem seçip, tek seferde sipariş yaratıyoruz.
     *    Gerçekte "sepete ekleme" mantığı gibi daha gelişmiş yapılara geçebilirsiniz.
     */
    @PostMapping
    public String createOrder(@RequestParam("menuItemId") Long menuItemId,
                              @RequestParam("quantity") int quantity,
                              @RequestParam("name") String name,
                              @RequestParam("description") String description) {
        MenuItem item = menuItemRepository.findById(menuItemId).orElse(null);
        if (item == null) {
            // Ürün bulunamazsa form sayfasına geri dön
            return "redirect:/orders/new";
        }

        // Kullanıcıdan alınan bilgilere göre yeni sipariş oluştur
        Order newOrder = new Order();
        newOrder.setName(name);
        newOrder.setDescription(description);
        newOrder.setPrice(item.getPrice() * quantity); // Toplam fiyatı hesapla

        // Sipariş kalemi (OrderItem) oluştur
        OrderItem orderItem = new OrderItem();
        orderItem.setMenuItem(item);
        orderItem.setQuantity(quantity);
        orderItem.setPrice(item.getPrice());
        orderItem.setOrder(newOrder);

        // Order ile OrderItem arasındaki iki yönlü ilişkiyi bağlayalım
        newOrder.getOrderItems().add(orderItem);

        // Siparişi kaydedelim (OrderItem da CascadeType.ALL sayesinde otomatik kaydolur)
        orderRepository.save(newOrder);

        // Oluşan siparişin detay sayfasına yönlendirme
        return "redirect:/orders/" + newOrder.getId();
    }
    /**
     * 4) Sipariş detayı
     */
    @GetMapping("/{id}")
    public String getOrderDetail(@PathVariable Long id, Model model) {
        Order order = orderRepository.findById(id).orElse(null);
        if (order == null) {
            return "redirect:/orders"; // veya 404 sayfası
        }
        model.addAttribute("order", order);
        return "order-detail"; // => src/main/resources/templates/order-detail.html
    }

    /**
     * 5) (İsteğe Bağlı) Siparişi silme
     */
    @GetMapping("/{id}/delete")
    public String deleteOrder(@PathVariable Long id) {
        orderRepository.deleteById(id);
        return "redirect:/orders";
    }

    // eğer Order entity'nizde "status" alanı yoksa, status güncelleme metodunu tamamen kaldırın
    /*
    // 6) Sipariş durumunu güncelle (Eğer entity'de 'status' field'ı yoksa bu kısım kullanılmaz)
    @PostMapping("/{id}/status")
    public String updateOrderStatus(@PathVariable Long id, @RequestParam("status") String status) {
        Order order = orderRepository.findById(id).orElse(null);
        if (order == null) {
            return "redirect:/orders";
        }
        // order.setStatus(status); // <--- BU ALAN Entity'de yoksa hata verir!
        orderRepository.save(order);
        return "redirect:/orders/" + id;
    }
    */
}
