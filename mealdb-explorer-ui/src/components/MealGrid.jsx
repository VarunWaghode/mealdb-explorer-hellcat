import React from "react";
import { MealCard } from "./MealCard";

const MealGrid = ({ meals, loading, setSelectedMeal }) => (
    loading ? (
        <div className="text-center text-xl text-gray-400 mt-20 animate-pulse">Loading...</div>
    ) : (
        <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 gap-8">
            {meals.length > 0 ? (
                meals.map((meal) => (
                    <MealCard key={meal.idMeal} meal={meal} onClick={() => setSelectedMeal(meal)} />
                ))
            ) : (
                <div className="col-span-full text-center text-gray-500 mt-10 text-xl">
                    No meals found.
                </div>
            )}
        </div>
    )
);

export default MealGrid;