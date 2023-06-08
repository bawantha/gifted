package com.bawantha.gifted.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;


@Data
@Entity(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 2000)
    private String description;

    @Column()
    private String link;

    @Column(nullable = false)
    private Date publishDate;


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Item other = (Item) obj;
        return id != null && id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", title='" + title + '\'' + ", description='" + description + '\'' + ", link='" + link + '\'' + ", publishDate=" + publishDate + '}';
    }

}
