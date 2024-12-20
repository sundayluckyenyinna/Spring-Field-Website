package com.springfield.website.common;
import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

public class SnakeCaseNamingStrategy extends PhysicalNamingStrategyStandardImpl {

    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment context) {
        return convertToSnakeCase(name);
    }

    @Override
    public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment context) {
        return convertToSnakeCase(name);
    }

    private Identifier convertToSnakeCase(Identifier name) {
        if (name == null) {
            return null;
        }
        String newName = name.getText()
                .replaceAll("([a-z])([A-Z])", "$1_$2")
                .toLowerCase();
        return Identifier.toIdentifier(newName);
    }
}
