// frontend/src/utils/presets.js
export function applyPreset(template, presetName) {
  const newTemplate = { ...template };

  switch (presetName) {
    case "standard":
      newTemplate.config = {
        ...newTemplate.config,
        cpu: 2,
        memory: "4GB",
        disk: "50GB",
      };
      break;

    case "memoryOptimized":
      newTemplate.config = {
        ...newTemplate.config,
        cpu: 4,
        memory: "16GB",
        disk: "100GB",
      };
      break;

    case "diskOptimized":
      newTemplate.config = {
        ...newTemplate.config,
        cpu: 4,
        memory: "8GB",
        disk: "200GB",
      };
      break;

    default:
      console.warn("Preset no reconocido:", presetName);
  }

  return newTemplate;
}
