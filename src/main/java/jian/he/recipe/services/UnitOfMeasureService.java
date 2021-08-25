package jian.he.recipe.services;

import jian.he.recipe.commands.UnitOfMeasureCommand;


import java.util.Set;

public interface UnitOfMeasureService {
    Set<UnitOfMeasureCommand> listAllUoms();
}
