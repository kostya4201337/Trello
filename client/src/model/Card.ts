export interface ICard {
    id: number,
    boardId: number,
    name: string,
    description: string,
    createdAt: Date,
    updatedAt: Date
}

export interface ICardCreation {
    name: string
    description: string
}