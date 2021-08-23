package jian.he.recipe.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@NoArgsConstructor
public class NoteCommand {
    private Long id;
    private String recipeNotes;
}
