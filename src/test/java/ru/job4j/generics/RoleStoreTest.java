package ru.job4j.generics;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RoleStoreTest {
    @Test
    public void whenAddAndFindThenRolenameIsWorker() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "Worker"));
        Role result = roleStore.findById("1");
        assertThat(result.getRolename(), is("Worker"));
    }

    @Test
    public void whenAddAndFindThenRoleIsNull() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "Employee"));
        Role result = roleStore.findById("2");
        assertNull(result);
    }

    @Test
    public void whenAddDuplicateAndFindRolenameIsManager() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("2", "Manager"));
        roleStore.add(new Role("2", "Worker"));
        Role result = roleStore.findById("2");
        assertThat(result.getRolename(), is("Manager"));
    }

    @Test
    public void whenReplaceThenRolenameIsExecutive() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "Employee"));
        roleStore.replace("1", new Role("1", "Executive"));
        Role result = roleStore.findById("1");
        assertThat(result.getRolename(), is("Executive"));
    }

    @Test
    public void whenNoReplaceRoleThenNoChangeRolename() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "Manager"));
        roleStore.replace("2", new Role("2", "Clerk"));
        Role result = roleStore.findById("1");
        assertThat(result.getRolename(), is("Manager"));
    }

    @Test
    public void whenDeleteRoleThenRoleIsNull() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("10", "Clerk"));
        roleStore.delete("10");
        Role result = roleStore.findById("10");
        assertNull(result);
    }

    @Test
    public void whenNoDeleteRoleThenRolenameIsManager() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Manager"));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getRolename(), is("Manager"));
    }
}