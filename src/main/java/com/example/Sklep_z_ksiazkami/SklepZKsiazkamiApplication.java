package com.example.Sklep_z_ksiazkami;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.Theme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Theme(value = "my-theme")
public class SklepZKsiazkamiApplication implements AppShellConfigurator {//co to robi??

	public static void main(String[] args)  {
		SpringApplication.run(SklepZKsiazkamiApplication.class, args);
	}

}
