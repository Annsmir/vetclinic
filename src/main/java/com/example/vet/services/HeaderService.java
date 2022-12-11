
package com.example.vet.services;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class HeaderService {

    /**
     * This method returns model attribute - additional menu item if admin is authorized.
     * If not admin, then this menu item will not appear.
     *
     * @return      the image at the specified URL
     */

    public String isUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getName().equals("admin")) {
            return "Добавить запись";
        }
        return "unauth";
    }

    public String isUserr() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getName().equals("admin")) {
            return "Все записи";
        }
        return "unauth";
    }

    public String usernameShow() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!auth.getName().equals("anonymousUser")) {
            return "Вы вошли как " + auth.getName();
        }
        return "unauth";
    }

    public String adminPageShow() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getName().equals("admin")) {
            return "Админ. страница";
        }
        return "unauth";
    }

    public String showAll() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getName().equals("admin")) {
            return "Все пациенты";
        }
        return "unauth";
    }

    public String showMy() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!auth.getName().equals("admin") && !auth.getName().equals("anonymousUser")  ) {
            return "Мои животные";
        }
        return "unauth";
    }
}
