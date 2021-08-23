package jian.he.recipe.converters;

import jian.he.recipe.commands.NoteCommand;
import jian.he.recipe.domain.Notes;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class NotesCommandToNotes implements Converter<NoteCommand, Notes> {

    @Nullable
    @Synchronized
    @Override
    public Notes convert(NoteCommand source){
        if(source == null){return null;}

        final Notes note = new Notes();
        note.setId(source.getId());
        note.setRecipeNotes(source.getRecipeNotes());
        return note;
    }
}
