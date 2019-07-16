/*
 * Copyright 2017, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hhmarket.mobile.db.entity;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import com.hhmarket.mobile.model.ProductInf;

@Entity(tableName = "products")
public class ProductEntity implements ProductInf {
    @PrimaryKey
    private int id;
    private String name;
    private String description;
    private int price;
    private String overrallRating;

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public String getOverrallRating() {

        return overrallRating;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public ProductEntity() {
    }

    @Ignore
    public ProductEntity(int id, String name, String description, int price, String overrallRating) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.overrallRating  = overrallRating;
    }

    public ProductEntity(ProductInf product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.overrallRating = product.getOverrallRating();
    }
}
