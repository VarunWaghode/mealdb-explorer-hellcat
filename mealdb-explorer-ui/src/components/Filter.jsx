import React from "react";

const Filters = ({
    categories,
    areas,
    ingredients,
    fetchRandom,
    filterByCategory,
    filterByArea,
    filterByIngredient,
}) => (
    <div className="mb-12 space-y-6">
        <div className="flex overflow-x-auto gap-3 pb-2 scrollbar-hide">
            <button
                onClick={fetchRandom}
                className="flex-shrink-0 px-6 py-2 bg-gray-900 border border-gray-700 rounded-full font-semibold text-gray-300 hover:bg-red-600 hover:text-white transition"
            >
                🎲 Random
            </button>
            {categories.map((cat) => (
                <button
                    key={cat.idCategory}
                    onClick={() => filterByCategory(cat.strCategory)}
                    className="flex-shrink-0 px-6 py-2 bg-gray-900 border border-gray-700 rounded-full font-semibold text-gray-300 hover:bg-red-600 hover:text-white transition"
                >
                    {cat.strCategory}
                </button>
            ))}
        </div>

        <div className="flex flex-col md:flex-row justify-center gap-4">
            <select
                defaultValue=""
                onChange={(e) => filterByArea(e.target.value)}
                className="bg-gray-900 border border-gray-700 py-3 px-6 rounded-full text-gray-300 font-medium focus:ring-2 focus:ring-red-600"
            >
                <option value="" disabled>
                    🌎 Filter by Area
                </option>
                {areas.map((a) => (
                    <option key={a.strArea} value={a.strArea}>
                        {a.strArea}
                    </option>
                ))}
            </select>

            <select
                defaultValue=""
                onChange={(e) => filterByIngredient(e.target.value)}
                className="bg-gray-900 border border-gray-700 py-3 px-6 rounded-full text-gray-300 font-medium focus:ring-2 focus:ring-red-600"
            >
                <option value="" disabled>
                    🥕 Filter by Ingredient
                </option>
                {ingredients.map((i) => (
                    <option key={i.idIngredient} value={i.strIngredient}>
                        {i.strIngredient}
                    </option>
                ))}
            </select>
        </div>
    </div>
);

export default Filters;