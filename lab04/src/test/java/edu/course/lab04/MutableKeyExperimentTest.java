package edu.course.lab04;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class MutableKeyExperimentTest {

    private static final class MutableKey {
        private String name;

        MutableKey(String name) {
            this.name = name;
        }

        void setName(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            return o instanceof MutableKey && name.equals(((MutableKey) o).name);
        }

        @Override
        public int hashCode() {
            return "before".equals(name) ? 0 : 1;
        }

        @Override
        public String toString() {
            return "MutableKey{" + name + "}";
        }
    }

    @Test
    void mutatingKeyAfterPutBreaksLookup() {
        Map<MutableKey, String> map = new HashMap<>();
        MutableKey key = new MutableKey("before");
        map.put(key, "value");

        assertEquals("value", map.get(key));

        key.setName("after");

        // (1) Поиск по ссылке на тот же объект не находит значение:
        //     hashCode изменился, HashMap ищет уже в другой корзине.
        assertNull(map.get(key));

        // (2) Поиск по новому экземпляру с тем же новым состоянием тоже не находит:
        //     equals теперь истинно для «after», но хэш ищет в корзине «after»,
        //     а запись лежит в корзине, выбранной для «before».
        assertNull(map.get(new MutableKey("after")));

        // (3) Поиск по исходному состоянию также не находит:
        //     он ищет в правильной корзине (как при вставке), но equals
        //     сравнивает с текущим (уже изменённым) состоянием сохранённого ключа.
        assertNull(map.get(new MutableKey("before")));

        // При этом запись не «потерялась» — размер по-прежнему 1,
        // но достать её обычными операциями уже нельзя.
        assertEquals(1, map.size());
    }
}