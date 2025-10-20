// frontend/src/utils/helpers.js

/**
 * Realiza una copia profunda del template y limpia campos que no deben enviarse
 * al backend. Ajusta las propiedades a eliminar según la estructura real de tu template.
 *
 * @param {Object} template - objeto de plantilla tal como está en el frontend
 * @returns {Object} - copia limpia lista para enviar al backend
 */
export function safePrepareForSend(template) {
  if (!template || typeof template !== "object") return template;

  // Deep clone: usa JSON para mayor compatibilidad; si tu template contiene
  // fechas o tipos especiales, considera usar structuredClone o una librería.
  const copy = JSON.parse(JSON.stringify(template));

  // Campos locales/temporales comunes a eliminar (adapta a tus props reales)
  // Ejemplos que suelen estar sólo en frontend:
  delete copy.localOnly;      // flag local temporal
  delete copy._temp;          // metadatos temporales
  delete copy.internalId;     // id interno de UI
  delete copy.__proto__;      // segurizar
  // Si guardas referencias a funciones, elementos DOM o clases, elimínalas:
  if (copy.vm && typeof copy.vm === "object") {
    // Ejemplo: si en vm tienes keys que no deben enviarse
    delete copy.vm._uiState;
    delete copy.vm._errors;
  }

  // Normalizaciones opcionales:
  // - Asegurarse de que firewallRules sea lista de strings, no coma-separated
  if (typeof copy.firewallRules === "string") {
    copy.firewallRules = copy.firewallRules
      .split(",")
      .map((r) => r.trim())
      .filter(Boolean);
  }

  // - Asegurarse de que campos numéricos sean numbers
  if (copy.cpu) copy.cpu = Number(copy.cpu);
  if (copy.memoria) copy.memoria = Number(copy.memoria);
  if (copy.almacenamiento) copy.almacenamiento = Number(copy.almacenamiento);
  if (copy.iops) copy.iops = Number(copy.iops);

  return copy;
}
