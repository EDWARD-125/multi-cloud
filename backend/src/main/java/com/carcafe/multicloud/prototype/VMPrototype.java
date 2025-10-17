package com.carcafe.multicloud.prototype;

/**
 * Interfaz base del patrón Prototype.
 * Define el contrato para clonar objetos VirtualMachine.
 */
public interface VMPrototype extends Cloneable {
    VMPrototype clone();
}
