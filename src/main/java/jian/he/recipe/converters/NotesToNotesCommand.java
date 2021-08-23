package jian.he.recipe.converters;

import jian.he.recipe.commands.NoteCommand;
import jian.he.recipe.domain.Notes;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class NotesToNotesCommand implements Converter<Notes, NoteCommand> {

    @Synchronized
    @Nullable
    @Override
    public NoteCommand convert(Notes source){
        if(source == null) {return null;}

        final NoteCommand noteCommand = new NoteCommand();
        noteCommand.setId(source.getId());
        noteCommand.setRecipeNotes(source.getRecipeNotes());
        return noteCommand;
    }
}
