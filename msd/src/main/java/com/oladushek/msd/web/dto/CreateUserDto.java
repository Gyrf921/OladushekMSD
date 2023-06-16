package com.oladushek.msd.web.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

/**
 Чтобы воспользоваться DTO-классом необходим механизм десериализации -
 превращения JSON-строки вида {"name": "John Doe"} в экземпляр класса
 CreateUserDto. Класс Builder реализует шаблон Строитель,
 который принято использовать в классах моделей и DTO
 */
@JsonDeserialize(builder = CreateUserDto.Builder.class)
public class CreateUserDto {

    private final String name;

    public static Builder builder(){return new Builder();}

    /**
     * Конструктор сделан закрытым, потому что объекты этого класса
     * надо порождать таким образом:
     * dto = CreateUserDto.builder().setName("John Doe").build()
     */
    private CreateUserDto(Builder builder) {
        this.name = builder.name;
    }

    public String getName() { return name; }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                '}';
    }

    /**
     * Подсказываем механизму десериализации,
     * что методы установки полей начинаются с set
     */
    @JsonPOJOBuilder(withPrefix = "set")
    public static class Builder {
        private String name;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public CreateUserDto build() { return new CreateUserDto(this); }
    }
}
