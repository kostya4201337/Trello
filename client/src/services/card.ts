import {ICard, ICardCreation} from "../model/Card";

export const getCardsService = (boardId: number): ICard[] => {
    return [
        {id: 1, boardId: 1, name: 'card1', description: 'desc1', createdAt: new Date(), updatedAt: new Date()},
        {id: 2, boardId: 1, name: 'card2', description: 'desc2', createdAt: new Date(), updatedAt: new Date()},
        {id: 3, boardId: 2, name: 'card3', description: 'desc3', createdAt: new Date(), updatedAt: new Date()}
    ];
};

export const addCardService = (boardId: number, newCard: ICardCreation): ICard => {
    return {id: Date.now(), boardId: boardId, name: newCard.name, description: newCard.description, createdAt: new Date(), updatedAt: new Date()};
}

export const deleteCardService = (id: number): void => {

};