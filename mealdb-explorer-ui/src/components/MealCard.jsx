import React from "react";

export const MealCard = ({ meal, onClick }) => (
    <div
        onClick={onClick}
        className="bg-gray-900 rounded-2xl overflow-hidden shadow-md hover:shadow-xl transition transform hover:-translate-y-2 cursor-pointer"
    >
        <div className="relative h-56 overflow-hidden">
            <img
                src={meal.strMealThumb}
                alt={meal.strMeal}
                className="w-full h-full object-cover transition duration-500 hover:scale-110"
            />
            <span className="absolute top-3 left-3 bg-black/60 px-3 py-1 rounded-full text-xs font-bold text-red-400 shadow">
                {meal.strCategory}
            </span>
        </div>
        <div className="p-5">
            <h3 className="text-lg font-bold text-gray-100 mb-1 truncate">{meal.strMeal}</h3>
            <p className="text-sm text-gray-400">📍 {meal.strArea}</p>
        </div>
    </div>
);
