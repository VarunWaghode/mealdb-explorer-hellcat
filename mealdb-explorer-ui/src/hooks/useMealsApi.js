import { useState, useEffect } from "react";

const API_URL = "http://localhost:8001/api/v1/meals";

export const useMealsApi = () => {
    const [meals, setMeals] = useState([]);
    const [categories, setCategories] = useState([]);
    const [areas, setAreas] = useState([]);
    const [ingredients, setIngredients] = useState([]);
    const [selectedMeal, setSelectedMeal] = useState(null);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);
    const [search, setSearch] = useState("");

    const performFetch = async (endpoint) => {
        setLoading(true);
        setError(null);
        try {
            const res = await fetch(`${API_URL}/${endpoint}`);
            if (!res.ok) throw new Error("Fetch failed");
            const data = await res.json();
            setMeals(Array.isArray(data) ? data : [data]);
        } catch (e) {
            setError("No meals found or backend offline.");
            setMeals([]);
        }
        setLoading(false);
    };

    const fetchRandom = () => performFetch("random");
    const filterByCategory = (c) => performFetch(`search?query=${c}`);
    const filterByArea = (a) => performFetch(`filter?area=${a}`);
    const filterByIngredient = (i) => performFetch(`filter?ingredient=${i}`);

    const handleSearch = (e) => {
        e.preventDefault();
        if (!search) return;
        performFetch(`search?query=${search}`);
    };

    useEffect(() => {
        fetchRandom();

        fetch(`${API_URL}/categories`).then((r) => r.json()).then(setCategories);
        fetch(`${API_URL}/areas`).then((r) => r.json()).then(setAreas);
        fetch(`${API_URL}/ingredients`).then((r) => r.json()).then((d) => setIngredients(d.slice(0, 100)));
    }, []);

    return {
        meals,
        categories,
        areas,
        ingredients,
        selectedMeal,
        setSelectedMeal,
        loading,
        error,
        search,
        setSearch,
        fetchRandom,
        filterByCategory,
        filterByArea,
        filterByIngredient,
        handleSearch,
    };
};
