// frontend/src/contexts/TemplateRegistry.jsx
import React, { createContext, useContext, useEffect, useState } from 'react';
import axios from 'axios';

const API_BASE = import.meta.env.VITE_API_URL || 'http://localhost:8080/api/v1';

export const TemplateRegistryContext = createContext(null);

export const useTemplateRegistry = () => {
  const ctx = useContext(TemplateRegistryContext);
  if (!ctx) throw new Error('useTemplateRegistry must be used inside TemplateRegistryProvider');
  return ctx;
};

export function TemplateRegistryProvider({ children }) {
  const [templates, setTemplates] = useState({});
  const api = axios.create({ baseURL: API_BASE, timeout: 12000 });

  useEffect(() => { refreshFromServer(); }, []);

  const list = () => Object.values(templates);
  const get = (id) => templates[id];

  const register = (t) => {
    setTemplates(prev => ({ ...prev, [t.id]: t }));
  };

  const refreshFromServer = async () => {
    try {
      const resp = await api.get('/templates');
      if (resp?.data && Array.isArray(resp.data)) {
        const map = Object.fromEntries(resp.data.map(t => [t.id, t]));
        setTemplates(map);
      }
    } catch (e) {
      console.error('TemplateRegistry.refreshFromServer:', e);
    }
  };

  const clone = (id, overrides = {}) => {
    const src = templates[id];
    if (!src) return undefined;
    const copy = (typeof structuredClone === 'function') ? structuredClone(src) : JSON.parse(JSON.stringify(src));
    // cleanup fields that shouldn't be reused
    copy.id = `local-${Date.now()}`;
    if (copy.metadata) { delete copy.metadata.internalId; }
    const merged = { ...copy, ...overrides };
    register(merged);
    return merged;
  };

  const remove = (id) => {
    setTemplates(prev => {
      const next = { ...prev };
      delete next[id];
      return next;
    });
  };

  return (
    <TemplateRegistryContext.Provider value={{ templates, list, get, register, clone, remove, refreshFromServer }}>
      {children}
    </TemplateRegistryContext.Provider>
  );
}
