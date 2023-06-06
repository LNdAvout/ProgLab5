package collectionUnit.collections;

import exception.IncorrectForm;

public abstract class Form<T> {
    public abstract T build() throws IncorrectForm;
}
