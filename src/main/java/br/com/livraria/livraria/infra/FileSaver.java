package br.com.livraria.livraria.infra;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.IOUtils;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileSaver {
	
	private final String baseFolder = "src\\main\\resources\\static\\img\\capa\\";
	
	/**
	 * 
	 * @param file
	 * @return 
	 *  lembrar que o tamanho não pode superar os 1048576 bytes ou 1,048576 mb.
	 */
	public String write(MultipartFile file) {
        try {
        	
        	byte[] bytes = file.getBytes();
        	Path path = Paths.get(baseFolder + file.getOriginalFilename());
        	Files.write(path, bytes);
        	
            return file.getOriginalFilename();
        } catch (IllegalStateException | IOException e) {
            throw new RuntimeException(e);
        }
    }
	
	@Deprecated
	public MultipartFile read(String caminho, String nome) {
		File file = new File(caminho);
		try {
			FileInputStream input = new FileInputStream(file);
			MultipartFile multipartFile = new MockMultipartFile(nome,
		            file.getName(), "image/jpeg", IOUtils.toByteArray(input));
			
			return multipartFile;
		} catch (IOException e) {
			throw new RuntimeException(e); 
		}
	}


	public void delete(String sumarioPath) {
		File file = new File(baseFolder + sumarioPath);
		
		file.delete();
	}

}
