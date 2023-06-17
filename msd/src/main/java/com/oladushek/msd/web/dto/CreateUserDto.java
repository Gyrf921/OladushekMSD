package com.oladushek.msd.web.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 Чтобы воспользоваться DTO-классом необходим механизм десериализации -
 превращения JSON-строки вида {"name": "John Doe"} в экземпляр класса
 CreateUserDto. Класс Builder реализует шаблон Строитель,
 который принято использовать в классах моделей и DTO
 */
//@JsonDeserialize(builder = CreateUserDto.Builder.class)
@Builder
@Data
public class CreateUserDto {


    @NotNull(message = "Key 'login' is mandatory")
    @Length(min = 5, max = 25, message = "Name length must be from 5 to 25")
    private final String login;

    @NotNull(message = "Column 'password' is mandatory")
    @Length(min = 5, max = 50, message = "Name length must be from 5 to 50")
    private final String password;

    @NotNull(message = "Column 'name' is mandatory")
    @Length(min = 5, max = 25, message = "Name length must be from 5 to 25")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Name must contain only letters a-z and A-Z")
    private final String name;

    @NotNull(message = "Column 'surname' is mandatory")
    @Length(min = 5, max = 25, message = "Name length must be from 5 to 25")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Name must contain only letters a-z and A-Z")
    private final String surname;

    @NotNull(message = "Column 'age' is mandatory")
    @Min(value = 0, message = "Age cannot be negative")
    @Max(value = 100, message = "Age cannot be more then 100")
    private final Integer age;


//    public static Builder builder(){return new Builder();}

    /**
     * Конструктор сделан закрытым, потому что объекты этого класса
     * надо порождать таким образом:
     * dto = CreateUserDto.builder().setName("John Doe").build()
     */
/*    private CreateUserDto(Builder builder) {
        this.name = builder.name;
    }

    public String getName() { return name; }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                '}';
    }*/

    /**
     * Подсказываем механизму десериализации,
     * что методы установки полей начинаются с set
     */
/*    @JsonPOJOBuilder(withPrefix = "set")
    public static class Builder {
        private String name;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public CreateUserDto build() { return new CreateUserDto(this); }
    }*/
}
