from typing import TypeVar, Self, Iterator

D = TypeVar('D')

class DynamicArray:
    
    def __init__(self):
        self._len = 0
        self._data : list[D | None] = [None]
        
    def __len__(self):
        return self._len
    
    def __getitem__(self, idx : int):  
        if idx >= len(self):
            raise IndexError("Out of Bounds")
        if idx < 0: 
            idx = len(self) + idx
        if idx < 0:
            raise IndexError("Out of Bounds")
        return self._data[idx]
    
    def __setitem__(self, idx : int, data : D):  
        if idx >= len(self):
            raise IndexError("Out of Bounds")
        if idx < 0:
            idx = len(self) + idx
        if idx < 0:
            raise IndexError("Out of Bounds")
        self._data[idx] = data
    
    def __repr__(self):
        return f"DArr:{self._data[:len(self)]}; OoB:{self._data[len(self):]}"
    
    def __eq__(self, other : Self):
        if not isinstance(other, DynamicArray):
            return False
        if len(self) != len(other):
            return False
        for i in range(len(self)):
            if self[i] != other[i]:
                return False
        return True
    
    @property
    def first(self) -> D:
        return self[0]
    
    @property
    def last(self) -> D:
        return self[-1]

    def __iter__(self) -> Iterator[D]:
        return iter(self._data[:len(self)])
                
    def contains(self, data : D) -> bool:
        for element in self:
            if element == data:
                return True
        return False
    
    def append(self, data : D) -> None:
        if self._len == len(self._data):  
            new_capacity = max(1, 2 * len(self._data))
            new_data = [None] * new_capacity
            for i in range(self._len):
                new_data[i] = self._data[i]
            self._data = new_data
        self._data[self._len] = data
        self._len += 1
         
       
    
    def remove(self, data : D)  -> None:
        found = False
        for i in range(self._len):
            if self._data[i] == data:
                found = True
                for j in range(i, self._len - 1):
                    self._data[j] = self._data[j + 1]
                self._data[self._len - 1] = None
                self._len -= 1
                break
        if not found:
            raise ValueError(f"{data} not found in array")
    
        if len(self._data) > 2 * self._len:
            new_capacity = max(1, 2 * self._len)
            new_data = [None] * new_capacity
            for i in range(self._len):
                new_data[i] = self._data[i]
            self._data = new_data


darr = DynamicArray()
for value in range(10):
    darr.append(value)
print(darr)
for val, comp in zip(darr, range(10)):
    assert val == comp, f"Der Wert {val} passt nicht zum Erwarteten {comp}."
    assert len(darr) >= len(darr._data) // 2, f"Das dynamische Array enthält zu viele leere Felder."

for value in range(0, 10, 2):
    darr.remove(value)
print(darr)
for val, comp in zip(darr, range(1, 10, 2)):
    assert val == comp, f"Der Wert {val} passt nicht zum Erwarteten {comp}."
    assert len(darr) >= len(darr._data) // 2, f"Das dynamische Array enthält zu viele leere Felder."

print(darr.contains(3))

print(darr.contains(6))
print("Alle Tests erfolgreich bestanden")
