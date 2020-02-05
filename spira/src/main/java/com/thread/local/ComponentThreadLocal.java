package com.thread.local;

import com.spira.component.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ComponentThreadLocal {

    public static Component getComponent(String name) {
        List<Component> components =
                _componentThreadLocals.get();

        for (Component component : components) {
            if (component.getName().equals(name)) {
                return component;
            }
        }

        return null;
    }

    public static void addComponent(Component component) {
        List<Component> components =
                _componentThreadLocals.get();

        components.add(component);

        _componentThreadLocals.set(components);
    }

    private static ThreadLocal<List<Component>> _componentThreadLocals
        = new ThreadLocal<>();
}
