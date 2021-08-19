package jian.he.recipe.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Notes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne //We don't need cascade option in here. Delete notes won't delete recipe.
    //On the other hand, delete recipe will delete notes(cascade).
    private Recipe recipe;
    @Lob
    private String recipeNotes;


}
