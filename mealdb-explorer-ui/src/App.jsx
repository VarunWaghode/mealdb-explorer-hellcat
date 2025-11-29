import React, { useState, useEffect } from "react";
import "./index.css";
import { useMealsApi } from "./hooks/useMealsApi";
import {Header, Filters, MealGrid, MealModal} from "./components"


export default function App() {
  const {
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
  } = useMealsApi();

  return (
    <div className="min-h-screen bg-[#0d0d0d] text-gray-200 font-sans">
      <Header search={search} setSearch={setSearch} handleSearch={handleSearch} />

      <div className="max-w-7xl mx-auto px-4 md:px-8 pt-20 pb-12">
        <Filters
          categories={categories}
          areas={areas}
          ingredients={ingredients}
          fetchRandom={fetchRandom}
          filterByCategory={filterByCategory}
          filterByArea={filterByArea}
          filterByIngredient={filterByIngredient}
        />

        {error && (
          <div className="text-center text-red-400 text-xl font-semibold mt-10">{error}</div>
        )}

        <MealGrid meals={meals} loading={loading} setSelectedMeal={setSelectedMeal} />
      </div>

      {selectedMeal && (
        <MealModal meal={selectedMeal} onClose={() => setSelectedMeal(null)} />
      )}

      <footer className="bg-gray-700 p-4">Made by Varun Waghode</footer>
    </div>
  );
}
