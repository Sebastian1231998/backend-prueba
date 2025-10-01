
/**
 * Hallar todos los pares de índices cuyos valores sumen el target.
 *
 * Supuestos:
 * - El número objetivo no puede ser 0
 * - El número objetivo puede ser negativo
 * - El número a sumar no puede exceder el índice
 * - La lista puede tener elementos duplicados y estar desordenada
 * - Puede haber varias combinaciones que den el resultado, se devolverán todas
 * - Complejidad en el peor caso: O(N^2)
 *
 * @param {number[]} list - Lista de enteros
 * @param {number} target - Número objetivo
 * @returns {number[][] | null} - Lista de pares de índices o null si no hay solución
 */
const getIndexesBySumList = (list, target) => {
    const result = [];
    for (let i = 0; i < list.length; i++) {
        for (let j = i + 1; j < list.length; j++) {
            if (list[i] + list[j] === target) {
                result.push([i, j]);
            }
        }
    }
    return result.length > 0 ? result : null;
};


const lista = [0, 0, 0, 0, 0, 1, 1];
const target = 2;

console.log(getIndexesBySumList(lista, target));
// Output esperado: [[5,6]]