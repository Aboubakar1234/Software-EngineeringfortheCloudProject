import React, { useEffect, useState } from "react";
import axios from "axios";

const API_URL = "http://localhost:8080/api"; // Assurez-vous que votre backend tourne sur ce port

const App = () => {
  const [clients, setClients] = useState([]);
  const [sentiments, setSentiments] = useState([]);
  const [newClient, setNewClient] = useState({ email: "", telephone: "" });
  const [newSentiment, setNewSentiment] = useState({ texte: "", clientId: "", type: "" });

  useEffect(() => {
    fetchClients();
    fetchSentiments();
  }, []);

  const fetchClients = async () => {
    const response = await axios.get(`${API_URL}/client`);
    setClients(response.data);
  };

  const fetchSentiments = async () => {
    const response = await axios.get(`${API_URL}/sentiment`);
    setSentiments(response.data);
  };

  const handleAddClient = async () => {
    await axios.post(`${API_URL}/client`, newClient);
    setNewClient({ email: "", telephone: "" }); // Réinitialiser le formulaire
    fetchClients();
  };

  const handleAddSentiment = async () => {
    if (!newSentiment.texte || !newSentiment.clientId || !newSentiment.type) {
      alert("Veuillez remplir tous les champs !");
      return;
    }

    await axios.post(`${API_URL}/sentiment`, {
      texte: newSentiment.texte,
      type: newSentiment.type,
      client: { id: newSentiment.clientId }
    });

    setNewSentiment({ texte: "", clientId: "", type: "" }); // Réinitialiser le formulaire
    fetchSentiments();
  };

  const handleDeleteSentiment = async (id) => {
    await axios.delete(`${API_URL}/sentiment/${id}`);
    fetchSentiments();
  };

  return (
    <div className="min-h-screen bg-gray-100 flex flex-col items-center p-6">
      <h1 className="text-3xl font-bold mb-6">Gestion des Clients et Sentiments</h1>

      {/* Formulaire d'ajout de client */}
      <div className="bg-white shadow-md rounded-lg p-6 w-full max-w-lg mb-6">
        <h2 className="text-xl font-semibold mb-4">Ajouter un Client</h2>
        <input
          type="text"
          placeholder="Email"
          value={newClient.email}
          onChange={(e) => setNewClient({ ...newClient, email: e.target.value })}
          className="w-full p-2 border rounded mb-2"
        />
        <input
          type="text"
          placeholder="Téléphone"
          value={newClient.telephone}
          onChange={(e) => setNewClient({ ...newClient, telephone: e.target.value })}
          className="w-full p-2 border rounded mb-2"
        />
        <button onClick={handleAddClient} className="w-full bg-blue-500 text-white py-2 rounded hover:bg-blue-600 transition">
          Ajouter
        </button>
      </div>

      {/* Liste des clients */}
      <div className="bg-white shadow-md rounded-lg p-6 w-full max-w-lg mb-6">
        <h2 className="text-xl font-semibold mb-4">Liste des Clients</h2>
        <ul>
          {clients.map((client) => (
            <li key={client.id} className="p-2 border-b hover:bg-gray-100">
              {client.email} - {client.telephone}
            </li>
          ))}
        </ul>
      </div>

      {/* Formulaire d'ajout de sentiment */}
      <div className="bg-white shadow-md rounded-lg p-6 w-full max-w-lg mb-6">
        <h2 className="text-xl font-semibold mb-4">Ajouter un Sentiment</h2>
        <input
          type="text"
          placeholder="Texte"
          value={newSentiment.texte}
          onChange={(e) => setNewSentiment({ ...newSentiment, texte: e.target.value })}
          className="w-full p-2 border rounded mb-2"
        />
        <select
          value={newSentiment.type}
          onChange={(e) => setNewSentiment({ ...newSentiment, type: e.target.value })}
          className="w-full p-2 border rounded mb-2"
        >
          <option value="">Sélectionner un type</option>
          <option value="POSITIF">Positif</option>
          <option value="NEGATIF">Négatif</option>
        </select>
        <select
          value={newSentiment.clientId}
          onChange={(e) => setNewSentiment({ ...newSentiment, clientId: e.target.value })}
          className="w-full p-2 border rounded mb-2"
        >
          <option value="">Sélectionner un client</option>
          {clients.map((client) => (
            <option key={client.id} value={client.id}>{client.email}</option>
          ))}
        </select>
        <button onClick={handleAddSentiment} className="w-full bg-green-500 text-white py-2 rounded hover:bg-green-600 transition">
          Ajouter
        </button>
      </div>

      {/* Liste des sentiments */}
      <div className="bg-white shadow-md rounded-lg p-6 w-full max-w-lg">
        <h2 className="text-xl font-semibold mb-4">Liste des Sentiments</h2>
        <ul>
          {sentiments.map((sentiment) => (
            <li key={sentiment.id} className="flex justify-between items-center p-2 border-b hover:bg-gray-100">
              <span>{sentiment.texte} - {sentiment.type} {sentiment.client && ` (Client: ${sentiment.client.email})`}</span>
              <button onClick={() => handleDeleteSentiment(sentiment.id)} className="bg-red-500 text-white px-3 py-1 rounded hover:bg-red-600 transition">
                Supprimer
              </button>
            </li>
          ))}
        </ul>
      </div>
    </div>
  );
};

export default App;
