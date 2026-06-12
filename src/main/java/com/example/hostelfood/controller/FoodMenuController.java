package com.example.hostelfood.controller;

import com.example.hostelfood.entity.FoodMenu;
import com.example.hostelfood.service.FoodMenuService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/menu")
@CrossOrigin(origins = "*")
public class FoodMenuController {

    @Autowired
    private FoodMenuService
            foodMenuService;

    // GET today's menu
    // URL:
    // GET /menu/today
    @GetMapping("/today")
    public ResponseEntity<
            List<FoodMenu>
            >
    getTodayMenu() {

        List<FoodMenu> menu =
                foodMenuService
                        .getTodayMenu();

        return
                ResponseEntity
                        .ok(menu);
    }

    // GET menu by date
    // URL:
    // GET /menu/date/2026-05-29
    @GetMapping(
            "/date/{date}"
    )
    public ResponseEntity<
            List<FoodMenu>
            >
    getMenuByDate(

            @PathVariable

            @DateTimeFormat(
                    iso =
                            DateTimeFormat
                                    .ISO
                                    .DATE
            )
            LocalDate date
    ) {

        List<FoodMenu> menu =
                foodMenuService
                        .getMenuByDate(
                                date
                        );

        return
                ResponseEntity
                        .ok(menu);
    }

    // GET meal type
    // URL:
    // GET /menu/meal/LUNCH
    @GetMapping(
            "/meal/{mealType}"
    )
    public ResponseEntity<
            List<FoodMenu>
            >
    getMealByType(

            @PathVariable
            String mealType
    ) {

        List<FoodMenu> meal =
                foodMenuService
                        .getMealByType(
                                mealType
                        );

        return
                ResponseEntity
                        .ok(meal);
    }

    // GET week menu
    // URL:
    // GET /menu/week
    @GetMapping("/week")
    public ResponseEntity<
            List<FoodMenu>
            >
    getWeekMenu() {

        List<FoodMenu> menu =
                foodMenuService
                        .getWeekMenu();

        return
                ResponseEntity
                        .ok(menu);
    }

    // GET all menus
    // URL:
    // GET /menu/all
    @GetMapping("/all")
    public ResponseEntity<
            List<FoodMenu>
            >
    getAllMenus() {

        return
                ResponseEntity
                        .ok(
                                foodMenuService
                                        .getAllMenus()
                        );
    }

    // POST add menu
    // URL:
    // POST /menu
    @PostMapping
    public ResponseEntity<
            FoodMenu
            >
    addMenuItem(

            @RequestBody
            FoodMenu foodMenu
    ) {

        FoodMenu saved =
                foodMenuService
                        .addMenuItem(
                                foodMenu
                        );

        return
                ResponseEntity
                        .status(201)
                        .body(saved);
    }

    // PUT update menu
    // URL:
    // PUT /menu/1
    @PutMapping("/{id}")
    public ResponseEntity<
            FoodMenu
            >
    updateMenuItem(

            @PathVariable
            Integer id,

            @RequestBody
            FoodMenu foodMenu
    ) {

        FoodMenu updated =
                foodMenuService
                        .updateMenuItem(
                                id,
                                foodMenu
                        );

        if (updated == null) {

            return
                    ResponseEntity
                            .notFound()
                            .build();
        }

        return
                ResponseEntity
                        .ok(updated);
    }

    // DELETE menu item
    // URL:
    // DELETE /menu/1
    @DeleteMapping("/{id}")
    public ResponseEntity<
            String
            >
    deleteMenuItem(

            @PathVariable
            Integer id
    ) {

        String msg =
                foodMenuService
                        .deleteMenuItem(
                                id
                        );

        return
                ResponseEntity
                        .ok(msg);
    }
}