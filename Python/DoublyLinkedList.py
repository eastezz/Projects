from typing import TypeVar, Self

D = TypeVar('D')
    
class Element:
    
    def __init__(self, data : D):
        self.data = data
        self.predecessor = self
        self.successor = self 
    
    @property  
    def has_successor(self) -> bool:
        return self.successor is not self

    @property
    def has_predecessor(self) -> bool:
        return self.predecessor is not self
    
    def __repr__(self):
        return str(self.data)

class DoublyLinkedList:
        
    def __init__(self):
        dummy = Element(None)

        self._head : Element = dummy
        self._tail : Element = dummy
        self._len : int = 0
        
    @property
    def first(self) -> Element:
        return self._head
    
    @property
    def last(self) -> Element:
        return self._tail
    
    def __repr__(self):
        elements = [str(element) for element in self]
        return f"DLL:⮎{'-'.join(elements)}⮌"
    
    def __len__(self):
        return self._len
    
    def __iter__(self):
        current = self.first
        while current.has_successor:  
            yield current.successor
            current = current.successor
    
    def contains(self, data : D) -> bool:
        for element in self:
            if element.data == data:
                return True
        return False
    
    def append(self, data : D) -> None:
        new_el = Element(data)
        last = self._tail
        last.successor = new_el
        new_el.predecessor = last
        self._tail = new_el
        self._len += 1
        
        
    
    def remove(self, data : D) -> None:
        current = self._head
        while current is not self._tail.successor:
            if current.data == data:
                last = current.predecessor
                next = current.successor
                if current is self._tail:
                    self._tail = last
                last.successor = next
                if next is not None:
                    next.predecessor = last
                self._len -= 1
                return
            current = current.successor   
            
    
    def __eq__(self, other : Self):
        if not isinstance(other, DoublyLinkedList):
            return False
        if len(self) != len(other):
            return False
        for a, b in zip(self, other):
            if a.data != b.data:
                return False
        return True


dll = DoublyLinkedList()
for value in range(10):
    dll.append(value)
print(dll)
for elm, comp in zip(dll, range(10)):
    assert elm.data == comp, f"Der Wert {elm.data} passt nicht zum Erwarteten {comp}."

for value in range(0, 10, 2):
    dll.remove(value)
print(dll)
for elm, comp in zip(dll, range(1, 10, 2)):
    assert elm.data == comp, f"Der Wert {elm.data} passt nicht zum Erwarteten {comp}."

print(dll.contains(3))

print(dll.contains(6))
print("Alle Tests erfolgreich bestanden")
