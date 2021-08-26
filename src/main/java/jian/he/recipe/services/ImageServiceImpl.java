package jian.he.recipe.services;

import jian.he.recipe.domain.Recipe;
import jian.he.recipe.repositroies.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;

@Service
@Slf4j
public class ImageServiceImpl implements ImageService{
    private final RecipeRepository recipeRepository;

    public ImageServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }


    @Override
    @Transactional
    public void saveImageFile(Long recipeId, MultipartFile file) {
        try{
            Recipe recipe = recipeRepository.findById(recipeId).get();

            Byte[] byteObjects = new Byte[file.getBytes().length];
            int i = 0;

            for(byte b: file.getBytes()){
                byteObjects[i++] = b;
            }
            recipe.setIamge(byteObjects);
            recipeRepository.save(recipe);

        }catch (IOException e){
            log.error("Error Occurred",e);
        }

        log.debug("Received a file.");
    }
}
