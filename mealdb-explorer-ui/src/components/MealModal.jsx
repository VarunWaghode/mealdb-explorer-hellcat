import React from "react";

const MealModal = ({ meal, onClose }) => {
    const getIngredients = (meal) => {
        let list = [];
        const source = meal.extraDetails || meal;
        for (let i = 1; i <= 20; i++) {
            const ing = source[`strIngredient${i}`];
            const measure = source[`strMeasure${i}`];
            if (ing && ing.trim()) list.push(`${ing} - ${measure}`);
        }
        return list;
    };

    return (
        <div
            className="fixed inset-0 z-50 flex items-center justify-center bg-black/70 backdrop-blur-sm p-4"
            onClick={onClose}
        >
            <div
                className="bg-gray-900 w-full max-w-4xl max-h-[90vh] rounded-3xl overflow-hidden shadow-2xl relative flex flex-col"
                onClick={(e) => e.stopPropagation()}
            >
                {/* Close Button */}
                <button
                    onClick={onClose}
                    className="absolute top-4 right-4 bg-gray-800 text-red-400 w-10 h-10 rounded-full flex items-center justify-center text-xl shadow"
                >
                    ✕
                </button>

                {/* Header Image Section */}
                <div className="relative h-64 md:h-80 flex-none">
                    <img src={meal.strMealThumb} alt={meal.strMeal} className="w-full h-full object-cover" />
                    <div className="absolute inset-0 bg-gradient-to-t from-black/80 to-transparent flex items-end p-8">
                        <div>
                            <h2 className="text-3xl font-extrabold text-white mb-2">{meal.strMeal}</h2>
                            <div className="flex gap-2">
                                <span className="bg-red-600 px-3 py-1 rounded-full text-sm">{meal.strCategory}</span>
                                <span className="bg-orange-600 px-3 py-1 rounded-full text-sm">{meal.strArea}</span>
                            </div>
                        </div>
                    </div>
                </div>

                {/* Scrollable Content */}
                <div className="overflow-y-auto p-8 space-y-6 flex-1">
                    <div className="grid md:grid-cols-3 gap-8">
                        {/* Ingredients */}
                        <div>
                            <h3 className="text-xl font-bold text-red-400 mb-4">🛒 Ingredients</h3>
                            <ul className="space-y-2 text-gray-300 text-sm">
                                {getIngredients(meal).map((i, idx) => (
                                    <li key={idx} className="border-b border-gray-700 pb-1">
                                        {i}
                                    </li>
                                ))}
                            </ul>
                        </div>

                        {/* Instructions */}
                        <div className="md:col-span-2">
                            <h3 className="text-xl font-bold text-red-400 mb-4">👩‍🍳 Instructions</h3>
                            <p className="text-gray-300 leading-relaxed whitespace-pre-line mb-6">
                                {meal.strInstructions}
                            </p>

                            {meal.strYoutube && (
                                <a
                                    href={meal.strYoutube}
                                    target="_blank"
                                    rel="noreferrer"
                                    className="inline-block bg-red-600 hover:bg-red-700 text-white px-6 py-3 rounded-xl font-bold shadow-lg"
                                >
                                    📺 Watch Video
                                </a>
                            )}
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default MealModal;
