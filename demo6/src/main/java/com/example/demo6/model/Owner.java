package com.example.demo6.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 超市拥有者，超市老板
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Owner {
    String name;
    int age;
}