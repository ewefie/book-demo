package com.example.booksdemo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.internal.constraintvalidators.hv.ISBNValidator;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity(name = "polish_book")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PolishBook {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Valid
    @Length(min = 2)
    @Column(name = "title")
    @NotNull
    private String title;

    @Valid
    @Column(name = "author")
    @NotNull
    private String author;

    @Valid
    @Min(1)
    private int pagesNum;

    @Valid
    private String isbn;

    @JsonIgnore
    @AssertTrue
    private boolean isAuthorValid() {
        String[] splitAuthor = author.split(" ");
        if (splitAuthor.length > 3) return false;
        for (String partOfName : splitAuthor) {
            if (!Character.isUpperCase(partOfName.charAt(0))) return false;
        }
        return true;
    }

    @JsonIgnore
    @AssertTrue
    private boolean isISBNValid() {
        if (isbn == null) return false;
        //dodac usuwanie liter
        String onlyNumISBN = isbn.replaceAll("-", "");
        final int isbnLength = onlyNumISBN.length();
        if (isbnLength != 10 && isbnLength != 13) return false;
        if (isbnLength == 10) {
            return validateIsbn10(onlyNumISBN);
        }
        return validateIsbn13(onlyNumISBN);
    }

    private boolean validateIsbn10(final String isbn) {
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += i * isbn.charAt(i);
        }
        return isbn.charAt(9) == sum % 11 || (isbn.charAt(9) == 'X' && sum % 11 == 10);
    }

    //this one needs check
    private boolean validateIsbn13(final String isbn) {
        int sum = 0;
        for (int i = 0; i < 12; i++) {
            sum += (i % 2 == 0) ? isbn.charAt(i) : isbn.charAt(i) * 3;
        }
        int checksum = 10 - (sum % 10);
        if (checksum == 10) {
            checksum = 0;
        }
        return checksum == Integer.parseInt(isbn.substring(12));
    }
}
